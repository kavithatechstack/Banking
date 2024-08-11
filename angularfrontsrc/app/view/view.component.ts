import { Component, Input } from '@angular/core';
import { UserInfo } from 'os';
import { User } from '../user/user';
import { UserServiceService } from '../user-service.service';
import { CommonModule } from '@angular/common';
import { AddComponent } from '../add/add.component';
import { DeleteComponent } from '../delete/delete.component';

@Component({
  selector: 'app-view',
  standalone: true,
  imports: [AddComponent,DeleteComponent],
  templateUrl: './view.component.html',
  styleUrl: './view.component.css',
  host: {ngSkipHydration: 'true'}
})
export class ViewComponent {
  editUser: boolean=false;
  deleteUserConfirm: boolean=false;
  usertoDelete :User;
  usersList : User[]=[]
  constructor(private userService:UserServiceService) {
  

}
ngOnInit()
{
 this.getAllUsers()
}
    getAllUsers() {
      this.userService.viewListOfPlayers().subscribe((res: any) => {
      //  console.log(res);
        this.usersList = res;
        //console.log('UsersList--------------->', this.usersList);
      });
    }
    onUpdateClicked()
    {
      this.editUser=true;
    }
    onDeleteClicked(user:User)
    {
      console.log('from delete component')
      this.deleteUserConfirm=true;
      this.usertoDelete=user;
    }

    deleteUserOnConfirmation(value:boolean)
    {
      this.deleteUserConfirm=false;
      window.location.reload();
    }
 
  }