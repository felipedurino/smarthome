import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { delay } from 'rxjs/operators';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class MockUserService {
  
  private users: User[] = [];

  createUser(user: User): Observable<User> {
    console.log('Mock: Criando usuário com dados:', user);
    
    // Simular validações
    if (!user.name || user.name.trim().length < 2) {
      return throwError(() => ({ 
        status: 400, 
        error: { message: 'Nome deve ter pelo menos 2 caracteres' } 
      }));
    }
    
    if (!user.email || !user.email.includes('@')) {
      return throwError(() => ({ 
        status: 400, 
        error: { message: 'Email deve ter formato válido' } 
      }));
    }
    
    if (!user.identification || user.identification.toString().length !== 11) {
      return throwError(() => ({ 
        status: 400, 
        error: { message: 'CPF deve ter 11 dígitos' } 
      }));
    }
    
    if (!user.birthDate) {
      return throwError(() => ({ 
        status: 400, 
        error: { message: 'Data de nascimento é obrigatória' } 
      }));
    }
    
    if (!user.password || user.password.length < 6) {
      return throwError(() => ({ 
        status: 400, 
        error: { message: 'Senha deve ter pelo menos 6 caracteres' } 
      }));
    }
    
    // Verificar se email já existe
    const existingUser = this.users.find(u => u.email === user.email);
    if (existingUser) {
      return throwError(() => ({ 
        status: 409, 
        error: { message: 'Email já está em uso' } 
      }));
    }
    
    // Verificar se CPF já existe
    const existingCpf = this.users.find(u => u.identification === user.identification);
    if (existingCpf) {
      return throwError(() => ({ 
        status: 409, 
        error: { message: 'CPF já está em uso' } 
      }));
    }
    
    // Criar usuário
    const newUser: User = {
      id: this.users.length + 1,
      name: user.name.trim(),
      identification: user.identification,
      birthDate: user.birthDate,
      email: user.email.trim(),
      password: undefined // Não retornar senha
    };
    
    this.users.push(newUser);
    
    console.log('Mock: Usuário criado com sucesso:', newUser);
    
    // Simular delay de rede
    return of(newUser).pipe(delay(1000));
  }
}





