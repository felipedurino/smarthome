import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../services/auth.service';
import { ResidenceService } from '../../services/residence.service';
import { Residence } from '../../models/residence.model';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  residenceForm: FormGroup;
  isLoading = false;
  hasResidence = false;
  currentResidence: Residence | null = null;
  userEmail: string = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private residenceService: ResidenceService,
    private snackBar: MatSnackBar
  ) {
    this.residenceForm = this.fb.group({
      street: ['', [Validators.required, Validators.minLength(3)]],
      houseNumber: ['', [Validators.required]],
      zipCode: ['', [Validators.required, Validators.pattern(/^\d{8}$/)]],
      state: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(2)]]
    });
  }

  ngOnInit(): void {
    // Obter email do usuário logado
    const token = this.authService.getToken();
    if (token) {
      // Decodificar token JWT para obter email (implementação simples)
      try {
        const payload = JSON.parse(atob(token.split('.')[1]));
        this.userEmail = payload.sub || 'Usuário';
      } catch (error) {
        this.userEmail = 'Usuário';
      }
    }

    this.checkExistingResidence();
  }

  checkExistingResidence(): void {
    // Para simplificar, vamos assumir que o usuário tem ID 1
    // Em uma aplicação real, você obteria o ID do usuário do token JWT
    const userId = 1;
    
    this.residenceService.checkResidenceExists(userId).subscribe({
      next: (exists) => {
        this.hasResidence = exists;
        if (exists) {
          this.loadResidence(userId);
        }
      },
      error: (error) => {
        console.error('Erro ao verificar residência:', error);
      }
    });
  }

  loadResidence(userId: number): void {
    this.residenceService.getResidenceByUser(userId).subscribe({
      next: (residence) => {
        this.currentResidence = residence;
        this.residenceForm.patchValue({
          street: residence.street,
          houseNumber: residence.houseNumber,
          zipCode: residence.zipCode,
          state: residence.state
        });
      },
      error: (error) => {
        console.error('Erro ao carregar residência:', error);
      }
    });
  }

  onSubmitResidence(): void {
    if (this.residenceForm.valid) {
      this.isLoading = true;
      
      const residenceData: Residence = {
        ...this.residenceForm.value,
        user: { id: 1 } as any // Em uma aplicação real, você obteria o usuário do token
      };

      this.residenceService.createResidence(residenceData).subscribe({
        next: (response) => {
          this.snackBar.open('Residência cadastrada com sucesso!', 'Fechar', {
            duration: 3000
          });
          this.hasResidence = true;
          this.currentResidence = response;
        },
        error: (error) => {
          this.snackBar.open('Erro ao cadastrar residência. Tente novamente.', 'Fechar', {
            duration: 3000
          });
          this.isLoading = false;
        },
        complete: () => {
          this.isLoading = false;
        }
      });
    }
  }

  logout(): void {
    this.authService.logout();
    this.snackBar.open('Logout realizado com sucesso!', 'Fechar', {
      duration: 3000
    });
  }
}
