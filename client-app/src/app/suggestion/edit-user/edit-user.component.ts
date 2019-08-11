import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UserService } from '@ikubinfo/core/services/user.service';

@Component({
  selector: 'ikubinfo-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  edit: FormGroup;
  constructor(private fb: FormBuilder,private userService: UserService) { }

  ngOnInit() {
    
  }

}
