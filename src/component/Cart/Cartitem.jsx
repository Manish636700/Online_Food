import { Chip, IconButton } from '@mui/material';
import React from 'react'
import RemoveCircleOutlineIcon from '@mui/icons-material/RemoveCircleOutline';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { removeCartItem, updateCartItem } from '../State/Cart/Action';

export const Cartitem = ({item}) => {

    const {auth,cart} = useSelector((store)=>store);
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const jwt = auth.jwt || localStorage.getItem("jwt");
    if (!item || !item.food) {
        return null;
      }
    const handleUpdateCartItem = (value) => {
        if (value === -1 && item.quantity === 1) {
            return handleRemoveCartItem();
        }
        const newQuantity = item.quantity + value;
        if (newQuantity > 0) {
          const data = { cartItemId: item.id, quantity: newQuantity };
          dispatch(updateCartItem({ data, jwt }));
        }
    };
    
    const handleRemoveCartItem = () => {
        dispatch(removeCartItem({ cartItemId: item.id, jwt: auth.jwt || jwt }));
    };
    

  return (
    <div className='px-5'>
        <div className='lg:flex items-center lg:space-x-5'>
            <div>
                <img className='w-[7rem] h-[7rem] object-cover' 
                src={item.food.images} 
                alt="Image not Loading"></img>
            </div>
            <div className='flex items-center justify-between lg:w-[70%]'>
                <div className='space-y-1 lg:space-y-3 w-full'>
                    <p className="text-left">{item.food.name}</p>
                    <div className='flex justify-between items-center'>
                        <div className='flex items-center space-x-1'>
                            <IconButton onClick={() => {handleUpdateCartItem(-1);setTimeout(() => window.location.reload(), 300)}}>
                                <RemoveCircleOutlineIcon/>
                            </IconButton>
                            <div className='w-5 h-5 text-xs flex items-center justify-center'>
                                {item.quantity}
                            </div>
                            <IconButton onClick={() => {handleUpdateCartItem(1); setTimeout(() => window.location.reload(), 300) }}>
                                <AddCircleOutlineIcon/>
                            </IconButton>

                        </div>
                    </div>

                </div>
                <p>₹{item.totalPrice}</p>

            </div>
        </div>
        <div className="pt-3 space-x-2 text-left">
            {item.ingredients.map((ingredient)=> <Chip label={ingredient}/>)}
        </div>
    </div>
  );
};