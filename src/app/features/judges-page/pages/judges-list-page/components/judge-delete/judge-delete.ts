import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { ImportsModules } from '@shared/models/imports.module';
import { JudgesStore } from 'src/app/features/judges-page/store/judges.store';

@Component({
  selector: 'judge-delete',
  imports: [
    ImportsModules,
  ],
  templateUrl: './judge-delete.html',
  styleUrl: './judge-delete.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class JudgeDelete {

  readonly store = inject(JudgesStore);

  confirmDelete(){
    this.store.deleteById();
  }
  closeDialog(){
    this.store.closeDialog();
  }

}
