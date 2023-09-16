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
<Routes>
<Route path="/home" element={<Home />}/>
</Routes>
</BrowserRouter>
{/**     <div className="overflow-x-hidden">
        <Nav />
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
