import React, { useEffect, useState } from "react";
import axios from "axios";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Checkbox,
  Button,
  Typography,
  Paper,
} from "@mui/material";
import "../Styles/StudentDataTable.css";
import FetchSelectedStudents from "./FetchSelectedStudents";

const StudentDataTable = () => {
  const [students, setStudents] = useState([]);
  const [selectedStudents, setSelectedStudents] = useState(
    JSON.parse(localStorage.getItem("selectedStudents")) || []
  );
  const [showStudentList, setShowStudentList] = useState(true);

  const fetchStudents = () => {
    axios
      .get("http://localhost:8080/student")
      .then((response) => {
        setStudents(response.data);
      })
      .catch((error) => console.log(error));
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  const handleSelect = (student) => {
    if (
      students.filter((s) => s.mentor_id === 1).length < 4 &&
      student.is_mentor_assigned === false
    ) {
      axios
        .put(`http://localhost:8080/mentor/assign/1/${student.student_id}`)
        .then(() => {
          fetchStudents();
          const updatedSelectedStudents = [...selectedStudents, student];
          setSelectedStudents(updatedSelectedStudents);
          localStorage.setItem(
            "selectedStudents",
            JSON.stringify(updatedSelectedStudents)
          );
        })
        .catch((error) => {
          console.log(error);
        });
    } else if (selectedStudents.some((s) => s.is_mentor_assigned === true)) {
      alert("Student is already selected.");
    } else {
      alert("Mentor cannot select more than 4 students.");
    }
  };

  const handleRemove = (student) => {
    axios
      .put(`http://localhost:8080/mentor/deassign/1/${student.student_id}`)
      .then(() => {
        fetchStudents();
        const updatedSelectedStudents = selectedStudents.filter(
          (s) => s.student_id !== student.student_id
        );
        setSelectedStudents(updatedSelectedStudents);
        localStorage.setItem(
          "selectedStudents",
          JSON.stringify(updatedSelectedStudents)
        );
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleEvaluate = () => {
    if (selectedStudents.length >= 3) {
      setShowStudentList(false);
    } else {
      alert("You must select at least 3 students for evaluation.");
    }
  };

  const toggleToStudentList = () => {
    setShowStudentList(true);
  };

  return (
    <div>
      <Typography variant="h4" gutterBottom>
        Student Data
      </Typography>
      {showStudentList ? (
        <TableContainer component={Paper}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Student ID</TableCell>
                <TableCell>Student Name</TableCell>
                <TableCell>Mentor ID</TableCell>
                <TableCell>Actions</TableCell>
                <TableCell>Remove</TableCell>
                <TableCell>Status</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {students.map((student) => (
                <TableRow key={student.id}>
                  <TableCell>{student.student_id}</TableCell>
                  <TableCell>{student.name}</TableCell>
                  <TableCell>
                    {student.mentor_id !== -1
                      ? student.mentor_id
                      : "Not Assigned"}
                  </TableCell>
                  <TableCell>
                    <Button
                      onClick={() => handleSelect(student)}
                      variant="contained"
                      color="primary"
                      style={{
                        backgroundColor: student.is_mentor_assigned
                          ? "green"
                          : "blue",
                        color: "white",
                      }}
                    >
                      {student.is_mentor_assigned ? "Selected" : "Select"}
                    </Button>
                  </TableCell>
                  <TableCell>
                    <Button
                      onClick={() => handleRemove(student)}
                      variant="contained"
                      color="secondary"
                      style={{ color: "white" }}
                    >
                      Remove
                    </Button>
                  </TableCell>
                  <TableCell>
                    <Checkbox
                      checked={student.is_evaluation_final}
                      color="primary"
                    />
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
          <div>
            <Button
              variant="contained"
              color="primary"
              onClick={handleEvaluate}
              disabled={selectedStudents.length < 3}
            >
              Evaluate
            </Button>
            {selectedStudents.length < 3 && (
              <Typography color="error">
                Please select at least 3 students to proceed further.
              </Typography>
            )}
          </div>
        </TableContainer>
      ) : (
        <div>
          {<FetchSelectedStudents />}
          <Button variant="contained" onClick={toggleToStudentList}>
            Back to Student List
          </Button>
        </div>
      )}
    </div>
  );
};

export default StudentDataTable;
