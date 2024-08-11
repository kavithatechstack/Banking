import { Routes } from '@angular/router';

//export const routes: Routes = [];
import { HomeComponent } from './home/home.component';

import { UpdateComponent } from './update/update.component';
import { ViewComponent } from './view/view.component';
import { AddComponent } from './add/add.component';
import { DeleteComponent } from './delete/delete.component';

//export const routes: Routes = [];
export const routes: Routes = [
    {path:'',component:HomeComponent},   
    {path:'update',component:UpdateComponent},
    {path:'viewUsers',component:ViewComponent},
    {path:'add',component:AddComponent},
    {path:'delete',component:DeleteComponent}
  ];

