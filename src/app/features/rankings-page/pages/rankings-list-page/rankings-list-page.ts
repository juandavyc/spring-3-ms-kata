import { CrudPageOption } from '@shared/ui/enums/crud-page-option.enum';
import { ChangeDetectionStrategy, Component, effect, inject, OnInit } from '@angular/core';
import { ImportsModules } from '@shared/models/imports.module';
import { RankingsStore } from '../../store/rankings.store';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-rankings-list-page',
  imports: [
    ImportsModules,
  ],
  templateUrl: './rankings-list-page.html',
  styleUrl: './rankings-list-page.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [MessageService]
})
export default class RankingsListPage implements OnInit {

  readonly store = inject(RankingsStore);
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
