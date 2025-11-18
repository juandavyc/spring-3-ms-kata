import { JsonPipe } from '@angular/common';
import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { EvaluationCreateRequest } from '@features/evaluations-page/models/evaluation-create-request.model';
import { EvaluationStore } from '@features/evaluations-page/store/evaluations.store';
import { ImportsModules } from '@shared/models/imports.module';
import { FormValidatorService } from '@shared/utils/form-validator.service';

@Component({
  selector: 'evaluation-create',
  imports: [
    ImportsModules,
    ReactiveFormsModule,
    JsonPipe
  ],
  templateUrl: './evaluation-create.html',
  styleUrl: './evaluation-create.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EvaluationCreate {

  private fb = inject(FormBuilder);

  readonly store = inject(EvaluationStore);
  readonly formValidatorService = inject(FormValidatorService);

  form = this.fb.group({
    profileScore: this.fb.control<number>(0, Validators.required),
    communicationScore: this.fb.control<number>(0, Validators.required),
    technicalScore: this.fb.control<number>(0, Validators.required),
    extraPoints: this.fb.control<number>(0),
    notes: this.fb.control<string>('')
  })

  submit(): void {
    if (this.form.invalid || this.form.pristine) return;
    if (this.store.isLoadingDetails()) return;

    const request: EvaluationCreateRequest = {
      judgeId: this.store.judgeId(),
      participantId: this.store.id(),
      ...this.form.getRawValue(),
    }
    this.store.create(request);
  }

}
