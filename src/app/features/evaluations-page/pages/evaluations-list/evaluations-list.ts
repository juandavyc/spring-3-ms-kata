import { ChangeDetectionStrategy, Component, effect, inject, OnInit } from '@angular/core';
import { EvaluationStore } from '@features/evaluations-page/store/evaluations.store';
import { ImportsModules } from '@shared/models/imports.module';
import { CrudPageOption } from '@shared/ui/enums/crud-page-option.enum';
import { MessageService } from 'primeng/api';
import { EvaluationDelete } from './components/evaluation-delete/evaluation-delete';
import { EvaluationCreate } from './components/evaluation-create/evaluation-create';
import { EvaluationDetails } from './components/evaluation-details/evaluation-details';

@Component({
  selector: 'app-evaluations-list',
  imports: [
    ImportsModules,

    //
    EvaluationDelete,
    EvaluationCreate,
    EvaluationDetails,
  ],
  templateUrl: './evaluations-list.html',
  styleUrl: './evaluations-list.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [MessageService]

})
export default class EvaluationsList implements OnInit {

  readonly store = inject(EvaluationStore);
  private readonly messageService = inject(MessageService);

  readonly CrudPageOption = CrudPageOption;

  closeDialog() {
    this.store.closeDialog();
  }


  openDialog(id: string | null, option: CrudPageOption) {
    console.log("openDialog", id, option);
    this.store.openDialog(id, option);
  }

  messageTrigger = effect(() => {
    const message = this.store.message();
    if (message)
      this.messageService.add({ ...message });
  })


  ngOnInit(): void {
    this.store.searchEvaluationsParticipants();
  }

}
