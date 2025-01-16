import React, { useEffect, useState } from 'react'
import { Card, IconButton } from '@mui/material'

import FavoriteIcon from '@mui/icons-material/Favorite';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import Chip from '@mui/material/Chip';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { addToFavorite } from '../State/Authentication/Action';
import { isPresentInFavorites } from '../config/logic';


const RestaurantCard = ({item}) => {
    const navigate=useNavigate();
    const dispatch=useDispatch();
    const jwt=localStorage.getItem("jwt");
    const {auth}=useSelector(store=>store);

    const handleAddToFavorite=()=>{
        dispatch(addToFavorite({jwt,restaurantId:item.id}))
    }

    const handleNavigateToRestaurant=()=>{
        if(item.open)
        {
            navigate(`/restaurant/${item.address.city}/${item.name}/${item.id}`)
        }
    }
    const restaurantImages =item.images || [];
    const [currentImageIndex, setCurrentImageIndex] = useState(0);
    
        useEffect(() => {
            if (restaurantImages.length > 1) {
                const interval = setInterval(() => {
                    setCurrentImageIndex((prevIndex) =>
                        prevIndex === restaurantImages.length - 1 ? 0 : prevIndex + 1
                    );
                }, 3000); // Change image every 3 seconds
    
                return () => clearInterval(interval); // Cleanup on component unmount
            }
        }, [restaurantImages.length]);
    return (
        <Card className='w-[18rem]'>

            <div className={`${true ? "cursor-pointer" : "cursor-not-allowed" } relative`}
            >
                <img
                className="w-full h-[10rem] rounded-t-md object-cover"
                src={restaurantImages[currentImageIndex]}
                             alt={`restaurant-image-${currentImageIndex}`}
                             style={{ width: '100%', height: '20vh', objectFit: 'cover' }} // Ensure styles are applied inline if needed
                        />
                
                <Chip
                size="small"
                className="absolute top-2 left-2"
                color={item.open?"success" : "error"}
                label={item.open?"open" : "closed"}
                />
            </div>

            <div className='p-4 textPart lg:flex w-full justify-between'>
                <div className='space-y-1'>
                    <p onClick={handleNavigateToRestaurant} className='font-semibold text-lg cursor-pointer'>{item.name}</p>
                    <p className='text-gray-500 text-sm'>{item.description} </p>
                </div>
                <div>
                    <IconButton onClick={handleAddToFavorite}>
                         {isPresentInFavorites(auth.favorites,item) ? <FavoriteIcon/> : <FavoriteBorderIcon/>}
                    </IconButton>
                </div>

            </div>

        </Card>
    )
}
export default RestaurantCard