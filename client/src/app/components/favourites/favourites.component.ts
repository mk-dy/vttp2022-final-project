import { Component, OnInit } from '@angular/core';
import { Favourite } from 'src/app/models';
import { ProductService } from 'src/app/services/product.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-favourites',
  templateUrl: './favourites.component.html',
  styleUrls: ['./favourites.component.css']
})
export class FavouritesComponent implements OnInit {

  userId!: string
  favList: Favourite[] = []

  constructor(private tokenSvc: TokenStorageService, private productSvc: ProductService) { }

  ngOnInit(): void {
    // get userId
    this.userId = this.tokenSvc.getUser().id

    // get Fav List
    this.productSvc.getFavourites(this.userId).then(result => {
      this.favList = result as Favourite[]
    })



  }

  deleteFav(id: any) {
    console.log('the id',id)
    this.productSvc.deleteFavourite(id).then(result => {
      console.info(result)
      location.reload()
    })

    // call service to remove favourite
  }

}
