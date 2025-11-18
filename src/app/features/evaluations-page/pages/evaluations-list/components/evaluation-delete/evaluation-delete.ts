import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { EvaluationStore } from '@features/evaluations-page/store/evaluations.store';
import { ImportsModules } from '@shared/models/imports.module';

@Component({
  selector: 'evaluation-delete',
  imports: [
    ImportsModules,
  ],
  templateUrl: './evaluation-delete.html',
  styleUrl: './evaluation-delete.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EvaluationDelete {

  readonly store = inject(EvaluationStore);

  confirmDelete(){
    this.store.deleteById();
  }
  closeDialog(){
    this.store.closeDialog();
  }

}
