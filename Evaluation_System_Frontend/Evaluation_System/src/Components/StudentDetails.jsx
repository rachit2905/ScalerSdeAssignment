import React, { useState } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Checkbox,
  Button,
  Select,
  MenuItem,
  Typography,
  Paper,
} from "@mui/material";
import EvaluationForm from "./EvaluationForm";

const StudentDetails = ({ studentDetails }) => {
  const [openEvaluation, setOpenEvaluation] = useState(false);
  const [selectedStudent, setSelectedStudent] = useState(null);
  const [filter, setFilter] = useState("all");

  const handleEvaluateClick = (student) => {
    setSelectedStudent(student);
    setOpenEvaluation(true);
  };

  const handleEvaluationSubmit = (student, evaluationData) => {
    // Replace this with your actual API call to submit evaluation data
    // For now, we'll just mark the student as submitted in the state
    const updatedStudentDetails = studentDetails.map((s) =>
      s.id === student.id ? { ...s, is_evaluation_final: true } : s
    );

    // Update the state to mark the student as submitted
    // setStudentDetails(updatedStudentDetails);
    setSelectedStudent(null); // Clear selected student
    setOpenEvaluation(false);
  };

  const calculateTotalMarks = (student) => {
    if (student.is_evaluation_final) {
      // Calculate the sum of all evaluation criteria
      const totalMarks = student.evaluations.reduce(
        (sum, value) => sum + value,
        0
      );
      return totalMarks;
    }
    return 0; // Marks are initialized as 0 for students with pending evaluations
  };

  const handleFilterChange = (event) => {
    setFilter(event.target.value);
  };

  const filteredStudents = studentDetails.filter((student) => {
    if (filter === "all") {
      return true; // Show all students
    } else if (filter === "done") {
      return student.is_evaluation_final; // Show students with final evaluation done
    } else if (filter === "pending") {
      return !student.is_evaluation_final; // Show students with pending evaluation
    }
  });

  return (
    <div>
      <Typography variant="h4" gutterBottom>
        Student Evaluation Details
      </Typography>
      <div>
        <Select value={filter} onChange={handleFilterChange}>
          <MenuItem value="all">All Students</MenuItem>
          <MenuItem value="done">Final Evaluation Done</MenuItem>
          <MenuItem value="pending">Final Evaluation Pending</MenuItem>
        </Select>
      </div>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Student ID</TableCell>
              <TableCell>Student Name</TableCell>
              <TableCell>Final Evaluation Submitted</TableCell>
              <TableCell>Idea</TableCell>
              <TableCell>Execution</TableCell>
              <TableCell>Innovation</TableCell>
              <TableCell>Viva</TableCell>
              <TableCell>Report Submission</TableCell>
              <TableCell>Total Marks</TableCell>
              <TableCell>Action</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filteredStudents.map((student) => (
              <TableRow key={student.id}>
                <TableCell>{student.student_id}</TableCell>
                <TableCell>{student.name}</TableCell>
                <TableCell>
                  <Checkbox
                    checked={student.is_evaluation_final}
                    disabled
                    style={{
                      color: student.is_evaluation_final ? "green" : "default",
                    }}
                  />
                </TableCell>
                <TableCell>
                  {student.evaluations[0] > 0 ? student.evaluations[0] : 0}
                </TableCell>
                <TableCell>
                  {student.evaluations[1] > 0 ? student.evaluations[1] : 0}
                </TableCell>
                <TableCell>
                  {student.evaluations[2] > 0 ? student.evaluations[2] : 0}
                </TableCell>
                <TableCell>
                  {student.evaluations[3] > 0 ? student.evaluations[3] : 0}
                </TableCell>
                <TableCell>
                  {student.evaluations[4] > 0 ? student.evaluations[4] : 0}
                </TableCell>
                <TableCell>{calculateTotalMarks(student)}</TableCell>
                <TableCell>
                  <Button
                    onClick={() => handleEvaluateClick(student)}
                    disabled={student.is_evaluation_final}
                    variant="contained"
                    color="primary"
                  >
                    Evaluate
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <EvaluationForm
        open={openEvaluation}
        onClose={() => setOpenEvaluation(false)}
        student={selectedStudent}
        onEvaluate={(evaluationData) =>
          handleEvaluationSubmit(selectedStudent, evaluationData)
        }
      />
    </div>
  );
};

export default StudentDetails;
