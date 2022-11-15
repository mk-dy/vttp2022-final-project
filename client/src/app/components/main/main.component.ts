import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { Fabric, Product, User } from '../../models';
import { ProductService } from '../../services/product.service';
import { SearchService } from '../../services/search.service';


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  productList!: Product[]
  sub$!: Subscription
  searchForm!: FormGroup
  fabricList!: Fabric[]
  token!: string | null
  userDetails!: User

  constructor(private fb: FormBuilder, 
              private searchSvc: SearchService, 
              private productSvc: ProductService, 
              private router: Router,
              private authSvc: AuthService,
              private tokenStorageSvc: TokenStorageService,
              library: FaIconLibrary) {library.addIcons(faMagnifyingGlass); }

  ngOnInit(): void {
    
    this.token = this.tokenStorageSvc.getToken()
    let user = this.tokenStorageSvc.getUser()

    this.authSvc.getUserFromEmail(user.email).subscribe(
      data => {
        window.sessionStorage.setItem('userDetails', JSON.stringify(data));
      },
    )

    this.productSvc.showProducts()
    this.sub$ = this.productSvc.onShowProducts.subscribe(data => {
      this.productList = data
    })

    console.info('product check',this.productList)
    this.searchForm = this.createForm()
  }

  createForm(): FormGroup {
    return this.fb.group({
      query: this.fb.control<string>(''),
    })
  }

  performSearch() {
    const data = this.searchForm.value.query
    console.info('>>>> check data: ', data)
    this.searchSvc.findProduct(data)
    this.searchForm.reset()
    this.router.navigate(
      ['/search'],
      { queryParams: { query: data } }
    )
  }




}
