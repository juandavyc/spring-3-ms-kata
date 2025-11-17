import { ChangeDetectionStrategy, Component, computed, effect, input, signal } from '@angular/core';
import { AbstractControl, FormControl, ValidationErrors } from '@angular/forms';

@Component({
  selector: 'form-error',
  imports: [],
  templateUrl: './form-error.html',
  styleUrl: './form-error.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class FormError {

  errors = input.required<string []>();

}
