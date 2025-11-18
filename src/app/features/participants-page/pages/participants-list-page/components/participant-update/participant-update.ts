import { ChangeDetectionStrategy, Component, effect, inject, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { SelectModel } from '@core/models/select.model';
import { JOB_ROLES } from '@features/participants-page/data/job-roles.data';
import { FormError } from '@shared/components/form-error/form-error';
import { ImportsModules } from '@shared/models/imports.module';
import { FormValidatorService } from '@shared/utils/form-validator.service';
import { ParticipantRequest } from 'src/app/features/participants-page/models/participant-request.model';
import { ParticipantsStore } from 'src/app/features/participants-page/store/participants.store';

@Component({
  selector: 'participant-update',
  imports: [
    ImportsModules,
    ReactiveFormsModule,
    FormError,
  ],
  templateUrl: './participant-update.html',
  styleUrl: './participant-update.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ParticipantUpdate {

  private fb = inject(FormBuilder);
  readonly store = inject(ParticipantsStore);
  readonly jobRoles: SelectModel[] = [...JOB_ROLES];
  readonly formValidatorService = inject(FormValidatorService);


  form = this.fb.group({
    name: this.fb.control<string>('', [Validators.required]),
    email: this.fb.control<string>('', [Validators.required, Validators.email]),
    jobRole: this.fb.control<string>('', [Validators.required]),
  })

  trigger = effect(() => {
    const values = this.store.participant();
    if (values) {
      this.form.patchValue({
        name: values.name,
        email: values.email,
        jobRole: values.jobRole,
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
    const request: ParticipantRequest = this.form.getRawValue();
    this.store.update(request);
  }

  closeDialog() {
    this.store.closeDialog();
  }



}
