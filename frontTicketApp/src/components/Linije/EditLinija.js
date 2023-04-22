import React, { useCallback, useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Row, Col, Form, Button } from "react-bootstrap";
import TestAxios from "../../apis/TestAxios";

const EditLinija = () => {
    var navigate = useNavigate();
    const routeParams = useParams();
    var linijaId = routeParams.id;
    const [prevoznici, setPrevoznici] = useState([]);

    const getPrevoznici = () => {
        TestAxios.get("/prevoznici")
          .then((res) => {
            console.log(res);
            setPrevoznici(res.data);
          })
          .catch((error) => {
            console.log(error);
            alert("Error occured please try again!");
          });
      };

      useEffect(() => {
        getLinijaById(linijaId);
        getPrevoznici();
      }, []);

      var linija = {
        id: linijaId,
        brojMesta: -1,
        cenaKarte: -1,
        vremePolaska: '',
        destinacija: '',
        prevoznikId: -1,
        prevoznikNaziv: '',
      };
    
      const [updateLinija, setUpdateLinija] = useState(linija);


      const valueInputChanged = (e) => {
        let input = e.target;
        let name = input.name;
        let value = input.value;
    
        let lineFromState = { ...updateLinija };
        lineFromState[name] = value;
        setUpdateLinija(lineFromState);
      };


      const onPrevChange = (e) => {
        const value = e.target.value;
        const selectedPrev = prevoznici.find((prev) => prev.id === parseInt(value));
        if (selectedPrev) {
          setUpdateLinija((prev) => ({
            ...prev,
            prevoznikId: selectedPrev.id,
            prevoznikNaziv: selectedPrev.naziv,
          }));
        }
      };

      const getLinijaById = (linijaId) => {
        TestAxios.get("/linije/" + linijaId)
          .then((res) => {
            console.log(res);
            setUpdateLinija((prevState) => ({
              ...prevState,
              id: linijaId,
              brojMesta: res.data.brojMesta,
              cenaKarte: res.data.cenaKarte,
              vremePolaska: res.data.vremePolaska,
              destinacija: res.data.destinacija,
              prevoznikId: res.data.prevoznikId,
              prevoznikNaziv: res.data.prevoznikNaziv,
            }));
            console.log(updateLinija);
          })
          .catch((error) => {
            console.log(error);
            alert("Error occured please try again!");
          });
      };

      const edit = (e) => {
        e.preventDefault();

        const dateRegex = /^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]$/;

        if (!dateRegex.test(updateLinija.vremePolaska)) {
        alert("Datum i vreme nisu u odgovarajućem formatu. Molimo Vas unesite u formatu yyyy-mm-dd hh:mm.");
         return;
        }

      var params = {
        id: updateLinija.id,
        brojMesta: updateLinija.brojMesta,
        cenaKarte: updateLinija.cenaKarte,
        vremePolaska: updateLinija.vremePolaska,
        destinacija: updateLinija.destinacija,
        prevoznikId: updateLinija.prevoznikId,
        prevoznikNaziv: updateLinija.prevoznikNaziv,
      };
  
      TestAxios.put("/linije/" + updateLinija.id, params)
        .then((res) => {
          console.log(res);
          alert("Linija was edited successfully!");
          navigate("/linije");
        })
        .catch((error) => {
          console.log(error);
          alert("Error occured please try again!");
        });
    };

    return (
        <>
          <Row>
            <Col></Col>
            <Col xs="12" sm="10" md="8">
              <h1>Edit linije</h1>

              <Form>

                <Form.Group>
                  <Form.Label htmlFor="brojMesta">broj Mesta</Form.Label>
                  <Form.Control
                    id="brojMesta"
                    value={updateLinija.brojMesta}
                    name="brojMesta"
                    type="number"
                    onChange={(e) => valueInputChanged(e)}
                  />{" "}
                  <br />
                </Form.Group>

                <Form.Group>
                  <Form.Label htmlFor="cenaKarte">cena Karte</Form.Label>
                  <Form.Control
                    type="text"
                    value={updateLinija.cenaKarte}
                    id="cenaKarte"
                    name="cenaKarte"
                    onChange={(e) => valueInputChanged(e)}
                  />{" "}
                  <br />
                </Form.Group>
    
                <Form.Group>
                  <Form.Label htmlFor="bodovi">vreme Polaska</Form.Label>
                  <Form.Control
                    type="text"
                    value={updateLinija.vremePolaska}
                    id="vremePolaska"
                    name="vremePolaska"
                    onChange={(e) => valueInputChanged(e)}
                  />
                </Form.Group>

                <Form.Group>
                  <Form.Label htmlFor="destinacija">destinacija</Form.Label>
                  <Form.Control
                    type="text"
                    value={updateLinija.destinacija}
                    id="destinacija"
                    name="destinacija"
                    onChange={(e) => valueInputChanged(e)}
                  />
                </Form.Group>
                
                <Row>
                  <Col>
                    <Form.Group>
                      <Form.Label>prevoznik</Form.Label>
                      <Form.Select name="id" onChange={(e) => onPrevChange(e)}>
                        {prevoznici.map((prev) => {
                          return (
                            <option
                              key={prev.id}
                              value={prev.id}
                              selected={prev.id === updateLinija.prevoznikId}
                            >
                              {prev.naziv}
                            </option>
                          );
                        })}
                      </Form.Select>
                    </Form.Group>
                  </Col>
                  </Row>
                  

                <Button type="submit" onClick={edit}>
                  Edit
                </Button>
              </Form>
            </Col>
            <Col></Col>
          </Row>
        </>
      );


}

export default EditLinija;