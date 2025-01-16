import React from 'react'
import { AddressCard } from '../Cart/AddressCard'
import { Button, Card } from '@mui/material'
import AddLocationAltIcon from '@mui/icons-material/AddLocationAlt';

export const Address = () => {
  const createOrderUsingSelectedAddress=()=>{}
  const handleOpenAddressModel=()=>setOpen(true);
  const [open, setOpen] = React.useState(false);
  return (
    <div>
      <section className="lg:w-[100%] flex justify-center px-5 pb-10 lg:pb-0">
          <div>
            <h1 className="text-center font-semibold text-2xl py-10">
              Address
            </h1>
            <div className="flex gap-5 flex-wrap justify-center">
              {[1, 1, 1, 1, 1].map((item) => (
                <AddressCard handleSelectAddress={createOrderUsingSelectedAddress} item={item} showButton={true} />
              ))}

                  <Card className="flex gap-5 w-64 p-5">
                        <AddLocationAltIcon/>
                        <div className="space-y-3 text-gray-500">
                          <h1 className="font-semibold text-lg text-white text-left">Add New Address</h1> 
                            <Button
                              variant="outlined"
                              fullWidth
                              onClick={handleOpenAddressModel}
                            >Add</Button>
                        </div>
                      </Card>

            </div>
          </div>
        </section>
    </div>
  )
}
