import Nav from "./components/Nav"
import Banner from "./components/Banner"
import About from "./components/About"
import Vision from "./components/Vision"
import { BrowserRouter,Routes,Route } from "react-router-dom"
import Home from "./Home"

function App() {

  return (
    <>

<BrowserRouter>
<Nav />
<Routes>
<Route path="/home" element={<Home />}/>
<Route path="/about" element={<About/>}/>
</Routes>
</BrowserRouter>

{/**     <div className="overflow-x-hidden">
        
        <hr />
        <Banner />
        <About />
        <Vision />
        </div>
  */}
    </>
  )
}

export default App
