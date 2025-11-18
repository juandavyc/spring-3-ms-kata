import { ChangeDetectionStrategy, Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ImportsModules } from '@shared/models/imports.module';
import { MenuItem } from 'primeng/api';
import { MENU_DATA } from './data/menu.data';

@Component({
  selector: 'app-toolbar',
  imports: [
    ImportsModules,
    RouterLink,
  ],
  templateUrl: './app-toolbar.html',
  styleUrl: './app-toolbar.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AppToolbar {

  items: MenuItem[] = [...MENU_DATA];
  isVisible: boolean = false;

  openDrawer(): void {
    this.isVisible = true;
  }
  closeDrawer(): void {
    this.isVisible = false;
  }

}
