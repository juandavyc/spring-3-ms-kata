import { CrudPageOption } from '@shared/ui/enums/crud-page-option.enum';
import { JudgeModel } from '../../../../core/models/judge.model';
import { JsonPipe } from '@angular/common';
import { ChangeDetectionStrategy, Component, effect, inject, OnInit } from '@angular/core';
import { ImportsModules } from '@shared/models/imports.module';
import { JudgesStore } from '../../store/judges.store';
import { RouterLink } from '@angular/router';
import { JudgeDelete } from './components/judge-delete/judge-delete';
import { JudgeUpdate } from './components/judge-update/judge-update';
import { JudgeCreate } from './components/judge-create/judge-create';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-judges-list-page',
  imports: [
    ImportsModules,
    RouterLink,
    JudgeDelete,
    JudgeUpdate,
    JudgeCreate,
    // JsonPipe,
  ],
  templateUrl: './judges-list-page.html',
  styleUrl: './judges-list-page.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [MessageService]
})
export default class JudgesListPage implements OnInit {

  readonly store = inject(JudgesStore);
  private readonly messageService = inject(MessageService);

  readonly CrudPageOption = CrudPageOption;

  //isVisible = false;

  closeDialog() {
    //this.isVisible = false;
    //this.store.setVisible(false);
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

  ngOnInit(): void {
    this.store.search();
  }

}
