import { Component, Input, output, Output } from '@angular/core';
import { User } from '../user/user';
import { EventEmitter } from 'stream';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-delete',
  standalone: true,
  imports: [],
  templateUrl: './delete.component.html',
  styleUrl: './delete.component.css'
})
export class DeleteComponent {
  @Input() usertodelete: User;

  constructor(private userService:UserServiceService)
  {
    
  }
ondeleteConfirmation=output<boolean>();

  onConfirmationButtonClicked(value: boolean) {
    if (value) {
      this.userService.deleteUser(this.usertodelete).subscribe((res:string)=>{
        console.log(res);
      })
      //console.log("User Deleted", this.usertodelete);
    }
    this.ondeleteConfirmation.emit(value);
  }
}
