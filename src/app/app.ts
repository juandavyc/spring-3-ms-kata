import { Component, signal } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { ImportsModules } from '@shared/models/imports.module';

@Component({
  selector: 'app-root',
  imports: [
    ImportsModules,
    RouterOutlet,
    RouterLink,
  ],
  templateUrl: './app.html'
})
export class App {

  protected readonly title = signal('kata');

}
