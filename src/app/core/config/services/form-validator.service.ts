import { Injectable } from '@angular/core';
import { AbstractControl, FormArray, FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class FormValidatorService {

  constructor() { }

  public isInvalidControl(control: AbstractControl | null): boolean {
    if (!control) return false;
    return control.touched && control.errors ? true : false;
  }

  public getErrorControl(control: AbstractControl | null): string[] {
    return this.getError(control);
  }

  public isInvalidField(form: FormGroup, fieldName: string): boolean | null {
    const field = form.get(fieldName);
    if (field) {
      return field.touched && field.errors ? true : false
    }
    return null;
  }

  isInvalidFieldInArray(form: FormArray, index: number): boolean | null {
    const field = form.controls[index];
    if (field) {
      return field.touched && field.errors ? true : false
    }
    return null;
  }
  public isInvalidFieldInArrayInGroup(form: FormArray, index: number, field: string) {
    const tempField = form.at(index).get(field);
    if (tempField) {
      return tempField.touched && tempField.errors ? true : false
    }
    return null;
  }

  public getErrorField(form: FormGroup, fieldName: string): string[] {
    return this.getError(form.get(fieldName));
  }


  getError(field: any): string[] {
    if (field && field.errors) {
      const errors = Object.keys(field.errors);
      const messages: string[] = [];
      for (const error of errors) {
        switch (error) {
          case 'required':
            messages.push('El campo es requerido');
            break;
          case 'minlength':
            messages.push(`Debe tener mínimo ${field.errors['minlength'].requiredLength} caracteres`);
            break;
          case 'maxlength':
            messages.push(`Debe tener máximo ${field.errors['maxlength'].requiredLength} caracteres`);
            break;
          case 'minArray':
            messages.push(`Debe tener mínimo ${field.errors['minArray'].min} elemento${field.errors['minArray'].min > 1 ? 's' : ''}`);
            break;
          case 'maxArray':
            messages.push(`Debe tener máximo ${field.errors['maxArray'].max} elementos`);
            break;
          case 'min':
            messages.push(`El valor mínimo debe ser: ${field.errors['min'].min}`);
            break;
          case 'max':
            messages.push(`El valor máximo debe ser: ${field.errors['max'].max}`);
            break;
          case 'emailTaken':
            messages.push('El email ya está en uso');
            break;
          case 'minMaxLength':
            messages.push(`No cumple, mínimo: ${field.errors['minMaxLength'].min} máximo: ${field.errors['minMaxLength'].max}`);
            break;
          case 'email':
            messages.push('Formato de email inválido');
            break;
          case 'pattern':
            messages.push('El formato no es válido');
            break;
          default:
            messages.push('Error de validación');
        }
      }
      return messages;
    }
    return [];
  }
}
