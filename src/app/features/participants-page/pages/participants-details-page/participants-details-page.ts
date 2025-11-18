import { ChangeDetectionStrategy, Component, inject, OnInit, signal } from '@angular/core';
import { ParticipantsStore } from '../../store/participants.store';
import { ActivatedRoute, Router } from '@angular/router';
import { toSignal } from '@angular/core/rxjs-interop';
import { ParticipantModel } from '../../../../core/models/participant.model';
import { map } from 'rxjs';

@Component({
  selector: 'app-participants-details-page',
  imports: [],
  templateUrl: './participants-details-page.html',
  styleUrl: './participants-details-page.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export default class ParticipantsDetailsPage {

  //const data = inject(ActivatedRoute).snapshot.data['data'];

  private route = inject(ActivatedRoute);


  participant$ = toSignal<ParticipantModel | null>(
    this.route.data.pipe(map((d) => d['data'])),
    { initialValue: null }
  );

}
