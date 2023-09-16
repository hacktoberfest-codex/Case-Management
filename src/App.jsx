import Nav from "./components/Nav"
import Banner from "./components/Banner"
import About from "./components/About"
import Vision from "./components/Vision"

function App() {

  return (
    <>
      <div className="overflow-x-hidden">
        <Nav />
        <hr />
        <Banner />
        <About />
        <Vision />
        </div>
    </>
  )
}

export default App
