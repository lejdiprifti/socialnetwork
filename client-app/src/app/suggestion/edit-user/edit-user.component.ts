import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { UserService } from "@ikubinfo/core/services/user.service";
import { AuthService } from "@ikubinfo/core/services/auth.service";
import { User } from "@ikubinfo/core/models/user";
import { RegisterComponent } from "@ikubinfo/authentification/register/register.component";
import { DatePipe } from "@angular/common";
import { ConfirmationService } from "primeng/components/common/confirmationservice";
import { LoggerService } from "@ikubinfo/core/utilities/logger.service";
import { Router } from "@angular/router";
@Component({
  selector: "ikubinfo-edit-user",
  templateUrl: "./edit-user.component.html",
  styleUrls: ["./edit-user.component.css"]
})
export class EditUserComponent implements OnInit {
  editForm: FormGroup;
  passwordForm: FormGroup;
  user: User;
  updateUser: User;
  constructor(
    private confirmationService: ConfirmationService,
    private datePipe: DatePipe,
    private fb: FormBuilder,
    private userService: UserService,
    private authService: AuthService,
    private logger: LoggerService,
    private router: Router
  ) {}

  ngOnInit() {
    this.updateUser = {};
    this.updateUser.socialLinks = {};
    this.passwordForm = this.fb.group(
      {
        password: [
          "",
          Validators.pattern("(?=.*d)(?=.*[a-z])(?=.*[A-Z]).{8,}")
        ],
        repeatPassword: ["", []]
      },
      { validators: RegisterComponent.passwordMatch }
    );

    this.editForm = this.fb.group({
      firstName: ["" , Validators.required],
      lastName: ["",Validators.required],
      birthdate: ["", [RegisterComponent.isOldEnough]],
      email: [""],
      address: [""],
      job: [""],
      education: [""],
      bio: ["", Validators.maxLength(300)],
      facebook: [
        "",
        Validators.pattern(
          "(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?"
        )
      ],
      twitter: [
        "",
        Validators.pattern(
          "(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?"
        )
      ],
      linkedin: [
        "",
        Validators.pattern(
          "(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?"
        )
      ],
      instagram: [
        "",
        Validators.pattern(
          "(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?"
        )
      ]
    });
    this.fillForm();
  }

  fillForm(): void {
    this.userService.getUserById(this.authService.loggedUser.id).subscribe(res=>{
    this.user=res;
    const dateString = this.user.birthdate;
    const newDate = new Date(dateString);
    const convertedDate = this.datePipe.transform(newDate, "yyyy-MM-dd");
    this.editForm.get("firstName").setValue(this.user.firstName);
    this.editForm.get("lastName").setValue(this.user.lastName);
    this.editForm.get("job").setValue(this.user.job);
    this.editForm.get("education").setValue(this.user.education);
    this.editForm.get("bio").setValue(this.user.bio);
    this.editForm.get("facebook").setValue(this.user.socialLinks.facebook);
    this.editForm.get("twitter").setValue(this.user.socialLinks.twitter);
    this.editForm.get("instagram").setValue(this.user.socialLinks.instagram);
    this.editForm.get("linkedin").setValue(this.user.socialLinks.linkedin);
    this.editForm.get("birthdate").setValue(convertedDate);
    this.editForm.get("address").setValue(this.user.address);
    this.editForm.get("email").setValue(this.user.email);
    },
  err=>{
    this.logger.error("Error","Something bad happened.");
    });
   
  }
  updatePassword(): void {
    if (this.passwordForm.value.password !== "") {
      this.updateUser.password = this.passwordForm.value.password;
    }
    this.confirmationService.confirm({
      message: "Do you want to save your data?",
      header: "Save Confirmation",
      icon: "pi pi-info-circle",
      accept: () => {
        this.userService.update(this.user.id, this.updateUser).subscribe(
          res => {
            this.logger.success(
              "Success",
              "Your data were saved successfully!"
            );
          },
          err => {
            this.logger.error("Error", "Something bad happened.");
            this.router.navigate(["/suggestion/profile/edit"]);
          }
        );
      }
    });
  }
  updateData(): void {
    if (this.editForm.value.birthdate !== null) {
      this.updateUser.birthdate = this.editForm.value.birthdate;
    }
    if (this.editForm.value.address !== "") {
      this.updateUser.address = this.editForm.value.address;
    }
    if (this.editForm.value.firstName !== "") {
      this.updateUser.firstName = this.editForm.value.firstName;
    }
    if (this.editForm.value.lastName !== "") {
      this.updateUser.lastName = this.editForm.value.lastName;
    }
    if (this.editForm.value.job !== "") {
      this.updateUser.job = this.editForm.value.job;
    }
    this.updateUser.bio = this.editForm.value.bio;
    if (this.editForm.value.education !== "") {
      this.updateUser.education = this.editForm.value.education;
    }
    if (this.editForm.value.firstName !== ""){
      this.updateUser.firstName = this.editForm.value.firstName;
    }
    if (this.editForm.value.lastName !== ""){
      this.updateUser.lastName= this.editForm.value.lastName;
    }
    this.updateUser.socialLinks.facebook = this.editForm.value.facebook;

    this.updateUser.socialLinks.linkedin = this.editForm.value.linkedin;

    this.updateUser.socialLinks.instagram = this.editForm.value.instagram;
    this.updateUser.socialLinks.twitter = this.editForm.value.twitter;
    
    this.confirmationService.confirm({
      message: "Do you want to save your data?",
      header: "Save Confirmation",
      icon: "pi pi-info-circle",
      accept: () => {
        this.userService.update(this.user.id, this.updateUser).subscribe(
          res => {
            this.router.navigate(["suggestion/profile"]);
            this.logger.success("Success", "Data saved successfully!");
          },
          err => {
            this.logger.error("Error", "Something bad happened.");
            this.router.navigate(["/suggestion/profile/edit"]);
          }
        );
      }
    });
  }


  delete(): void {
    this.confirmationService.confirm({
      message: "Do you want to delete your account?",
      header: "Delete Confirmation",
      icon: "pi pi-info-circle",
      accept: () => {
        this.userService.delete(this.user.id).subscribe(res=>{
          this.logger.info("Info","Account was deleted.");
          this.router.navigate(['suggestion/login'])
        },err=>{
          this.logger.error("Error","Something bad happened.");
        });
    }
  });
}


}
