import { Component, signal } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { AppFooter } from '@shared/layout/app-footer/app-footer';
import { AppToolbar } from '@shared/layout/app-toolbar/app-toolbar';
import { ImportsModules } from '@shared/models/imports.module';

@Component({
  selector: 'app-root',
  imports: [
    ImportsModules,
    RouterOutlet,
    AppFooter,
    AppToolbar,

  ],
  templateUrl: './app.html'
})
export class App {

  protected readonly title = signal('kata');

}
