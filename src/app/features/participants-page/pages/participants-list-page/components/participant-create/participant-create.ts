import { ChangeDetectionStrategy, Component, effect, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators, FormControl } from '@angular/forms';
import { FormError } from '@shared/components/form-error/form-error';
import { ImportsModules } from '@shared/models/imports.module';
import { ParticipantsStore } from 'src/app/features/participants-page/store/participants.store';
import { isAvailableEmailValidator } from 'src/app/features/participants-page/validators/available-email.validator';
import { FormValidatorService } from '@core/config/services/form-validator.service';

@Component({
  selector: 'participant-create',
  imports: [
    ImportsModules,
    ReactiveFormsModule,
    FormError
],
  templateUrl: './participant-create.html',
  styleUrl: './participant-create.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ParticipantCreate {

  private fb = inject(FormBuilder);

  readonly store = inject(ParticipantsStore);
  readonly formValidatorService = inject(FormValidatorService);

  readonly form = this.fb.group({
    name: this.fb.control<string>('', [Validators.required]),
    email: this.fb.control<string>('',
      {
        validators: [Validators.required, Validators.email],
        asyncValidators: [isAvailableEmailValidator()],
        updateOn: 'blur',
      })
  })

public isInvalidControl(control: string ) {
    return this.formValidatorService.isInvalidControl(this.form.get(control));
  }

  public getErrorControl(control: string ){
    return this.formValidatorService.getErrorControl(this.form.get(control));
  }


  submit(): void {
    if (this.form.invalid || this.form.pristine) return;
    if (this.store.isLoadingDetails()) return;
    this.store.create(this.form.getRawValue());
  }
  closeDialog() {
    this.store.closeDialog();
  }
  resetEffect = effect(() => {
    const isDone = this.store.isDoneDetails();
    if (isDone) {
      this.form.reset();
    }
  })

}
