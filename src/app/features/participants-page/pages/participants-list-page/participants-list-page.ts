import { CrudPageOption } from '@shared/ui/enums/crud-page-option.enum';
import { ParticipantModel } from '../../../../core/models/participant.model';
import { JsonPipe } from '@angular/common';
import { ChangeDetectionStrategy, Component, effect, inject, OnInit } from '@angular/core';
import { ImportsModules } from '@shared/models/imports.module';
import { ParticipantsStore } from '../../store/participants.store';
import { RouterLink } from '@angular/router';
import { ParticipantDelete } from './components/participant-delete/participant-delete';
import { ParticipantUpdate } from './components/participant-update/participant-update';
import { ParticipantCreate } from './components/participant-create/participant-create';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-participants-list-page',
  imports: [
    ImportsModules,
    RouterLink,
    ParticipantDelete,
    ParticipantUpdate,
    ParticipantCreate,
    // JsonPipe,
  ],
  templateUrl: './participants-list-page.html',
  styleUrl: './participants-list-page.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [MessageService]
})
export default class ParticipantsListPage implements OnInit {

  readonly store = inject(ParticipantsStore);
  private readonly messageService = inject(MessageService);

  readonly CrudPageOption = CrudPageOption;

  ngOnInit(): void {
    this.store.search();
  }

  closeDialog() {
    this.store.closeDialog();
  }

  openDialog(id: string | null, option: CrudPageOption) {
    this.store.openDialog(id, option);
  }

  messageTrigger = effect(() => {
    const message = this.store.message();
    if (message)
      this.messageService.add({ ...message });
  })

}
