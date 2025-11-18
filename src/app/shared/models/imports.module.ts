
import { NgModule } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { ButtonGroupModule } from 'primeng/buttongroup';
import { DialogModule } from 'primeng/dialog';
import { Toast } from 'primeng/toast';
import { InputTextModule } from 'primeng/inputtext';
import { SliderModule } from 'primeng/slider';
import { TextareaModule } from 'primeng/textarea';
import { MenubarModule } from 'primeng/menubar';
import { DrawerModule } from 'primeng/drawer';
import { PanelMenuModule } from 'primeng/panelmenu';
import { Select } from 'primeng/select';

const PRIMENG_MODULES = [
  ButtonModule,
  TableModule,
  ButtonGroupModule,
  Toast,
  InputTextModule,
  DialogModule,
  SliderModule,
  TextareaModule,
  MenubarModule,
  DrawerModule,
  PanelMenuModule,
  Select
]
@NgModule({
  imports: [...PRIMENG_MODULES],
  exports: [...PRIMENG_MODULES],
})
export class ImportsModules { }
