
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { AuthService } from '@ikubinfo/core/services/auth.service';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { User } from '@ikubinfo/core/models/user';
import { RoleEnum } from '@ikubinfo/core/models/role.enum';

@Component({
  selector: 'ikubinfo-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  constructor(
    private router: Router,
    private fb: FormBuilder,
    private authService: AuthService,
    private logger: LoggerService
  ) {
    
   }

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
    
  }

  login(): void {
    this.authService.login(this.loginForm.value).subscribe(data => {
      this.authService.setData(data);
      if (this.authService.user.role.id === RoleEnum.USER){
      this.router.navigate(['/suggestion']);
      }else{
        this.router.navigate(['/posts']);
      }
      this.logger.success("Success","Logged in successfully!")
    },err=>{
      this.logger.info("Info","Invalid username or password");
    });
  }
}