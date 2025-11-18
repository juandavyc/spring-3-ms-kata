import { ChangeDetectionStrategy, Component, inject, OnInit, signal } from '@angular/core';
import { JudgesStore } from '../../store/judges.store';
import { ActivatedRoute, Router } from '@angular/router';
import { toSignal } from '@angular/core/rxjs-interop';
import { JudgeModel } from '../../../../core/models/judge.model';
import { map } from 'rxjs';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-judges-details-page',
  imports: [
    JsonPipe
  ],
  templateUrl: './judges-details-page.html',
  styleUrl: './judges-details-page.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export default class JudgesDetailsPage {

  //const data = inject(ActivatedRoute).snapshot.data['data'];

  private route = inject(ActivatedRoute);


  judge$ = toSignal<JudgeModel | null>(
    this.route.data.pipe(map((d) => d['data'])),
    { initialValue: null }
  );

}
