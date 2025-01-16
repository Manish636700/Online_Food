import { Card, CardActions, CardContent, CardMedia, IconButton, Typography } from '@mui/material'
import React from 'react'
import DeleteIcon from '@mui/icons-material/Delete';

export const EventCard = () => {
  return (
    <div>
        <Card sx={{width:345}}>
            <CardMedia
            sx={{height:445}}
            image='https://images.pexels.com/photos/842571/pexels-photo-842571.jpeg'></CardMedia>

            <CardContent>
                <Typography variant='h5' textAlign={'left'}>
                    Indian Fast Food
                </Typography>

                <Typography variant='body2' textAlign={'left'}>
                    50% off on your first order
                </Typography>

                <div className='py-2 space-y-2'>
                    <p className='text-left'>{"Mumbai"}</p>
                    <p className='text-left text-sm text-blue-500'>February 14, 2024 12:00 AM</p>
                    <p className='text-left text-sm text-red-500'>February 15, 2024 12:00 AM</p>
                </div>
            </CardContent>
            {false && <CardActions>
                <IconButton>
                    <DeleteIcon/>
                </IconButton>
                </CardActions>}
            <CardActions>

            </CardActions>


        </Card>
    </div>
  )
}
