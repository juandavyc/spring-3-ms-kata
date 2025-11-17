import { ChangeDetectionStrategy, Component, effect, inject, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ImportsModules } from '@shared/models/imports.module';
import { ParticipantRequest } from 'src/app/features/participants-page/models/participant-request.model';
import { ParticipantsStore } from 'src/app/features/participants-page/store/participants.store';

@Component({
  selector: 'participant-update',
  imports: [
    ImportsModules,
    ReactiveFormsModule,
  ],
  templateUrl: './participant-update.html',
  styleUrl: './participant-update.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ParticipantUpdate{

  private fb = inject(FormBuilder);
  readonly store = inject(ParticipantsStore);

  form = this.fb.group({
    name: this.fb.control<string>('', [Validators.required]),
    email: this.fb.control<string>('', [Validators.required, Validators.email])
  })

  trigger = effect(() => {
    const values = this.store.participant();
    if (values) {
      this.form.patchValue({
        name: values.name,
        email: values.email,
      })
    }
  })

  resetEffect = effect(() => {
    const isDone = this.store.isDoneDetails();
    if (isDone) {
      this.form.reset();
    }
  })
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

  closeDialog(){
    this.store.closeDialog();
  }



}
