import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-creation-status',
  templateUrl: './creation-status.component.html',
  styleUrls: ['./creation-status.component.css']
})
export class CreationStatusComponent implements OnInit {

  creationMsg!: string
  sub$!: Subscription

  constructor(private userSvc: UserService) { }

  ngOnInit(): void {
    this.sub$ = this.userSvc.onCreateAcc.subscribe(data => {
      this.creationMsg = data
    })
  }

}
