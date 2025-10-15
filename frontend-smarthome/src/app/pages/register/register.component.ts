import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../../services/user.service';
import { MockUserService } from '../../services/mock-user.service';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  registerForm: FormGroup;
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private mockUserService: MockUserService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {
    this.registerForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2)]],
      identification: ['', [Validators.required, Validators.pattern(/^\d{11}$/)]],
      birthDate: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required]]
    }, { validators: this.passwordMatchValidator });
  }

  passwordMatchValidator(form: FormGroup) {
    const password = form.get('password');
    const confirmPassword = form.get('confirmPassword');
    
    if (password && confirmPassword && password.value !== confirmPassword.value) {
      confirmPassword.setErrors({ passwordMismatch: true });
      return { passwordMismatch: true };
    }
    
    return null;
  }

  onSubmit(): void {
    if (this.registerForm.valid) {
      this.isLoading = true;
      
      // Formatar a data para yyyy-MM-dd
      const birthDate = this.registerForm.value.birthDate;
      const formattedDate = birthDate instanceof Date 
        ? birthDate.toISOString().split('T')[0] 
        : birthDate;
      
      const userData: User = {
        name: this.registerForm.value.name,
        identification: parseInt(this.registerForm.value.identification),
        birthDate: formattedDate,
        email: this.registerForm.value.email,
        password: this.registerForm.value.password
      };

      console.log('Dados sendo enviados:', userData);
      
      // Usar serviço real do backend
      this.userService.createUser(userData).subscribe({
        next: (response) => {
          console.log('Usuário criado com sucesso:', response);
          this.snackBar.open('Usuário criado com sucesso! Faça login para continuar.', 'Fechar', {
            duration: 5000
          });
          this.router.navigate(['/login']);
        },
        error: (error) => {
          console.error('Erro ao criar usuário:', error);
          let errorMessage = 'Erro ao criar usuário. Verifique os dados e tente novamente.';
          
          if (error.error && error.error.message) {
            errorMessage = error.error.message;
          } else if (error.status === 400) {
            errorMessage = 'Dados inválidos. Verifique se todos os campos estão corretos.';
          } else if (error.status === 409) {
            errorMessage = 'Email ou CPF já cadastrado.';
          }
          
          this.snackBar.open(errorMessage, 'Fechar', {
            duration: 5000
          });
          this.isLoading = false;
        },
        complete: () => {
          this.isLoading = false;
        }
      });
    }
  }

  goToLogin(): void {
    this.router.navigate(['/login']);
  }
}
