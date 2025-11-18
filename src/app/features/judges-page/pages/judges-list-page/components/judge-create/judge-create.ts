import { ChangeDetectionStrategy, Component, effect, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators, FormControl } from '@angular/forms';
import { FormError } from '@shared/components/form-error/form-error';
import { ImportsModules } from '@shared/models/imports.module';
import { FormValidatorService } from '@shared/utils/form-validator.service';
import { isAvailableEmailValidator } from '@features/participants-page/validators/available-email.validator';
import { JudgesStore } from '@features/judges-page/store/judges.store';
import { SPECIALIZATIONS } from '@features/judges-page/data/specialization.data';
import { SelectModel } from '@core/models/select.model';



@Component({
  selector: 'judge-create',
  imports: [
    ImportsModules,
    ReactiveFormsModule,
    FormError
  ],
  templateUrl: './judge-create.html',
  styleUrl: './judge-create.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class JudgeCreate {

  private fb = inject(FormBuilder);

  readonly store = inject(JudgesStore);
  readonly formValidatorService = inject(FormValidatorService);
  readonly specializations:SelectModel[] = [...SPECIALIZATIONS];


  readonly form = this.fb.group({
    name: this.fb.control<string>('', [Validators.required]),
    email: this.fb.control<string>('',
      {
        validators: [Validators.required, Validators.email],
        asyncValidators: [isAvailableEmailValidator()],
        updateOn: 'blur',
      }),
    specialization: this.fb.control<string>('', [Validators.required]),
  })

  public isInvalidControl(control: string) {
    return this.formValidatorService.isInvalidControl(this.form.get(control));
  }

  public getErrorControl(control: string) {
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
