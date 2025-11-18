import { JsonPipe } from '@angular/common';
import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { EvaluationStore } from '@features/evaluations-page/store/evaluations.store';
import { ImportsModules } from '@shared/models/imports.module';

@Component({
  selector: 'evaluation-details',
  imports: [
    ImportsModules,
    JsonPipe,
  ],
  templateUrl: './evaluation-details.html',
  styleUrl: './evaluation-details.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EvaluationDetails {

  readonly store = inject(EvaluationStore);

  closeDialog() {
    this.store.closeDialog();
  }
}
