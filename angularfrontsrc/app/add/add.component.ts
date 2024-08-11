import { Component, Output, ViewChild, viewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { User } from '../user/user';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-add',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './add.component.html',
  styleUrl: './add.component.css'
})
export class AddComponent {
  @ViewChild('templateForm') ngform: NgForm;
  user: User;
  savedUser: User;
  constructor(private userService:UserServiceService) {
  

  }

  OnFormSubmitted()
  {
    console.log(this.ngform.value);
   /* console.log(this.ngform.value.uname);
    console.log(this.ngform.value.address);
    console.log(this.ngform.value.phoneNum);
    console.log(this.ngform.value.gender);*/
 
      /*this.user.name= this.ngform.value.uname,
      this.user.phoneNum=this.ngform.value.phoneNum,
      this.user.address= this.ngform.value.address,
      this.user.gender= this.ngform.value.gender*/
       this.saveUserDetails()
    
  }
  saveUserDetails()
  {
    this.userService.addUser(this.ngform.value).subscribe((res: User)=>{
      console.log(res);
    this.savedUser=res;
  //  console.log(this.savedUser);
    })
  }


}
