import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClienteListComponent } from './component/cliente-list/cliente-list.component';
import { ClienteSaveComponent } from './component/cliente-save/cliente-save.component';
import { ClienteEditComponent } from './component/cliente-edit/cliente-edit.component';


const routes: Routes = [
  {path:'cliente-list',component:ClienteListComponent},
  {path:'cliente-save',component:ClienteSaveComponent},
  {path:'cliente-edit/:id',component:ClienteEditComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
