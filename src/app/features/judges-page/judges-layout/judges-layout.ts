import { ChangeDetectionStrategy, Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-judges-layout',
  imports: [RouterOutlet],
  template: `<router-outlet/>`,
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class JudgesLayout {

}
