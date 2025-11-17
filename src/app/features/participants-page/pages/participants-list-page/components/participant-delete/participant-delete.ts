import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { ImportsModules } from '@shared/models/imports.module';
import { ParticipantsStore } from 'src/app/features/participants-page/store/participants.store';

@Component({
  selector: 'participant-delete',
  imports: [
    ImportsModules,
  ],
  templateUrl: './participant-delete.html',
  styleUrl: './participant-delete.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ParticipantDelete {

  readonly store = inject(ParticipantsStore);

  confirmDelete(){
    this.store.deleteById();
  }
  closeDialog(){
    this.store.closeDialog();
  }

}
