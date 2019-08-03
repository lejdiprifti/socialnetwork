import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { RegisterService } from '@ikubinfo/core/services/register.service';
import { LoggerService } from '@ikubinfo/core/utilities/logger.service';
import { Router } from '@angular/router';
import { User } from '@ikubinfo/core/models/user';
import { SelectItem } from 'primeng/primeng';

@Component({
  selector: 'ikubinfo-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  
  registerForm: FormGroup;
  passwordForm: FormGroup;
  images: Array<SelectItem>;
  static isOldEnough(control: AbstractControl): any {
    const birthDatePlus18 = new Date(control.value);
    birthDatePlus18.setFullYear(birthDatePlus18.getFullYear() + 18);
    return birthDatePlus18 < new Date() ? null : { tooYoung: true };
  }
  
  static passwordMatch(group: FormGroup):any{
    const password= group.get('password').value;
    const repeatPassword= group.get('repeatPassword').value;
    return password === repeatPassword ? null : { matchingError: true};
  }

  constructor(private fb: FormBuilder,
    private registerService: RegisterService,
    private logger: LoggerService,
    private router: Router) {
     this.images=[
      {label: 'Batman',value:'Batman'},
      {label: 'Superman',value: 'Superman'},
      {label: 'Joker',value:'Joker'}
     ]
     }

  ngOnInit() {
    this.passwordForm=this.fb.group({
      password: [
        "", 
        [
          Validators.required,
          Validators.pattern("(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$")
        ]
      ],
      repeatPassword: [
        "",
        [
          Validators.required,
        ]
      ] 
    },
    {validators: RegisterComponent.passwordMatch});
  
    this.registerForm = this.fb.group({
      image: [""],
      firstName: [
        "",
        [
          Validators.required,
          Validators.minLength(3)
        ]
      ],
      lastName: [
        "",
        [
          Validators.required,
          Validators.minLength(3)
        ]
      ],
      birthdate: ["", [Validators.required, RegisterComponent.isOldEnough]],
      email: [
        "",
        [
          Validators.required,
          Validators.pattern("[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")
        ]
      ],
      passwordForm: this.passwordForm
     
    });
  }
  getData(): User{
    return{
    image:this.registerForm.value.image || 'Batman',
    firstName: this.registerForm.value.firstName,
    lastName: this.registerForm.value.lastName,
    birthdate: this.registerForm.value.birthdate,
    email: this.registerForm.value.email,
    password: this.passwordForm.value.password
    }
  }
  register(): void {
    this.registerService.register(this.getData()).subscribe(res=>{
      this.logger.success("Success","You registered successfully.");
      this.router.navigate(['/login']);
    },err=>{
      this.logger.error("Error","Email is already in use.");
    });
  }

}
