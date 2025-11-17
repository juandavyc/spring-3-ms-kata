
import { NgModule } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { ButtonGroupModule } from 'primeng/buttongroup';
import { DialogModule } from 'primeng/dialog';
import { Toast } from 'primeng/toast';
import { InputTextModule } from 'primeng/inputtext';

const PRIMENG_MODULES = [
  ButtonModule,
  TableModule,
  ButtonGroupModule,
  Toast,
  InputTextModule,
  DialogModule,
]
@NgModule({
  imports: [...PRIMENG_MODULES],
  exports: [...PRIMENG_MODULES],
})
export class ImportsModules { }
