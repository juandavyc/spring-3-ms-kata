import { ChangeDetectionStrategy, Component, effect, inject, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { SelectModel } from '@core/models/select.model';
import { SPECIALIZATIONS } from '@features/judges-page/data/specialization.data';
import { FormError } from '@shared/components/form-error/form-error';
import { ImportsModules } from '@shared/models/imports.module';
import { FormValidatorService } from '@shared/utils/form-validator.service';
import { JudgeRequest } from 'src/app/features/judges-page/models/judge-request.model';
import { JudgesStore } from 'src/app/features/judges-page/store/judges.store';

@Component({
  selector: 'judge-update',
  imports: [
    ImportsModules,
    ReactiveFormsModule,
    FormError,
  ],
  templateUrl: './judge-update.html',
  styleUrl: './judge-update.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class JudgeUpdate {

  private fb = inject(FormBuilder);
  readonly store = inject(JudgesStore);
  readonly formValidatorService = inject(FormValidatorService);

  readonly specializations: SelectModel[] = [...SPECIALIZATIONS];


  form = this.fb.group({
    name: this.fb.control<string>('', [Validators.required]),
    email: this.fb.control<string>('', [Validators.required, Validators.email]),
    specialization: this.fb.control<string>('', [Validators.required])
  })

  trigger = effect(() => {
    const values = this.store.judge();
    if (values) {
      this.form.patchValue({
        name: values.name,
        email: values.email,
        specialization: values.specialization,
      })
    }
  })

  resetEffect = effect(() => {
    const isDone = this.store.isDoneDetails();
    if (isDone) {
      this.form.reset();
    }
  })

  public isInvalidControl(control: string) {
    return this.formValidatorService.isInvalidControl(this.form.get(control));
  }

  public getErrorControl(control: string) {
    return this.formValidatorService.getErrorControl(this.form.get(control));
  }


  submit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    if (this.form.pristine) {
      return;
    }
    const request: JudgeRequest = this.form.getRawValue();
    this.store.update(request);
  }

  closeDialog() {
    this.store.closeDialog();
  }



}
