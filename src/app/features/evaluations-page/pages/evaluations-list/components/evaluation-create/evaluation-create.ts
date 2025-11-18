import { ChangeDetectionStrategy, Component, computed, inject, input } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { JudgeModel } from '@core/models/judge.model';
import { SelectModel } from '@core/models/select.model';
import { EvaluationCreateRequest } from '@features/evaluations-page/models/evaluation-create-request.model';
import { EvaluationStore } from '@features/evaluations-page/store/evaluations.store';
import { ImportsModules } from '@shared/models/imports.module';
import { FormValidatorService } from '@shared/utils/form-validator.service';

@Component({
  selector: 'evaluation-create',
  imports: [
    ImportsModules,
    ReactiveFormsModule,
  ],
  templateUrl: './evaluation-create.html',
  styleUrl: './evaluation-create.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EvaluationCreate {

  private fb = inject(FormBuilder);

  readonly judges = input.required<JudgeModel[]>();

  readonly store = inject(EvaluationStore);

  readonly formValidatorService = inject(FormValidatorService);

  judgedsOptions = computed<SelectModel[]>(() => {
    return this.judges().map(judge => ({
      name: judge.name,
      code: judge.id
    }))
  })

  form = this.fb.group({
    profileScore: this.fb.control<number>(0, Validators.required),
    communicationScore: this.fb.control<number>(0, Validators.required),
    technicalScore: this.fb.control<number>(0, Validators.required),
    extraPoints: this.fb.control<number>(0),
    judgeId: this.fb.control<string>('', Validators.required),
    notes: this.fb.control<string>('')
  })

  closeDialog() {
    this.store.closeDialog();
  }

  submit(): void {
    if (this.form.invalid || this.form.pristine) return;
    if (this.store.isLoadingDetails()) return;

    const request: EvaluationCreateRequest = {
      participantId: this.store.id(),
      ...this.form.getRawValue(),
    }
    this.store.create(request);
  }



}
