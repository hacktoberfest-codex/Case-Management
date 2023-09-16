import Typewriter from 'typewriter-effect';
import Slider from './Slider';

export default function () {

    return (
        <div className="relative" id='home'>
            <div className="">
            <Slider />
            </div>
            <div className="z-50 bottom-12 border-2 border-neutral-400 relative w-4/5 mx-auto h-[450px] bg-white shadow-xl rounded-3xl p-8">
                <div className='flex justify-between'>
                    <div className="text-5xl font-bold ">
                        <span className='mr-2 '>NOW CASE MANAGEMENT IS
                        <Typewriter className
                            options={{
                                strings: ['Well Ordered', 'Reliable', ' Faster'],
                                autoStart: true,
                                loop: true,
                            }}
                        /> <br /> </span>

                        <span className='font-serif text-3xl'>No more Pending Cases ...</span> <br /><br />
                        <div className="mt-30 gap-6 flex">
                            <a href="#" target='_blank'>
                                <img className='h-[70px]' src="/image/google-play-1.svg" alt="" />
                            </a>
                            <a href="#" target='_blank'>
                                <img className='h-[70px]' src="/image/app-store-1.svg" alt="" />
                            </a>
                        </div>
                    </div>
{/**
  <div className="">
                        <img className='w-96 mb-11 ml-36' src="" alt="" />
                    </div>
 */}
                   
                    {/* <p className="text-white text-center">Sign up for our newsletter to get the latest news, announcements and special offers.</p> */}
                </div>
            </div>
        </div>

    )
}
