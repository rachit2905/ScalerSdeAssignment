import React, { useState } from "react";
import axios from "axios";
import emailjs from "emailjs-com";
emailjs.init("bizWzdT5G2pNML0Ue");
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
  Grid,
  TextField,
  Typography,
  Box,
  Divider,
} from "@mui/material";

const EvaluationForm = ({ open, onClose, student, onEvaluate }) => {
  const [criteria, setCriteria] = useState({
    idea: 0,
    execution: 0,
    innovation: 0,
    viva: 0,
    reportSubmission: 0,
  });
  const [isSubmitted, setIsSubmitted] = useState(false);
  const [error, setError] = useState(null);
  const [isConfirmationOpen, setConfirmationOpen] = useState(false);

  const handleCriteriaChange = (event, name) => {
    const value = parseInt(event.target.value, 10) || 0;
    setCriteria({ ...criteria, [name]: value });
  };

  const handleOpenConfirmation = () => {
    setConfirmationOpen(true);
  };

  const handleCloseConfirmation = () => {
    setConfirmationOpen(false);
  };
  const handleEvaluate = async () => {
    if (isSubmitted) {
      setError("Student has already been evaluated.");
      return;
    }

    if (isConfirmationOpen) {
      // Close the confirmation dialog
      handleCloseConfirmation();
    } else {
      // Open the confirmation dialog
      handleOpenConfirmation();
      return;
    }

    const numbersArray = Object.values(criteria).filter(
      (value) => !isNaN(value)
    );
    const evaluationData = {
      student_id: student.student_id,
      evaluations: numbersArray,
      is_evaluation_final: true,
      mentor_id: student.mentor_id,
      is_mentor_assigned: student.is_mentor_assigned,
      name: student.name,
    };

    try {
      const response = await axios.put(
        `http://localhost:8080/student/evaluate/${student.student_id}`,
        evaluationData
      );

      if (response.status === 200) {
        setIsSubmitted(true);
        setError(null);
        console.log("Marks updated successfully.");

        // Send an email using EmailJS
        emailjs
          .send("service_9724clh", "template_sye2rkt", {
            to_email: student.email,
            message: `Hello, the final evaluation for ${student.name} has been submitted.`,
          })
          .then(
            (response) => {
              console.log("Email sent: ", response);
            },
            (error) => {
              console.error("Error sending email: ", error);
            }
          );
      } else {
        setError("Failed to update marks.");
      }
    } catch (error) {
      setError("An error occurred while updating marks.");
    }
  };

  return (
    <Dialog open={open} onClose={onClose} maxWidth="md" fullWidth>
      <DialogTitle>
        <Box p={2}>
          <Typography variant="h5">Student Evaluation</Typography>
          <Typography variant="h6">
            Student: {student ? student.name : ""}
          </Typography>
          <Divider />
        </Box>
      </DialogTitle>
      <DialogContent>
        {error && <Typography color="error">{error}</Typography>}
        <form>
          <Grid container spacing={2} sx={{ mt: 2 }}>
            <Grid container spacing={2} sx={{ mt: 2 }}>
              <Grid item xs={6}>
                <TextField
                  label="Idea (0-10)"
                  type="number"
                  fullWidth
                  InputProps={{ inputProps: { min: 0, max: 10 } }}
                  value={criteria.idea}
                  onChange={(e) => handleCriteriaChange(e, "idea")}
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  label="Execution (0-10)"
                  type="number"
                  fullWidth
                  InputProps={{ inputProps: { min: 0, max: 10 } }}
                  value={criteria.execution}
                  onChange={(e) => handleCriteriaChange(e, "execution")}
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  label="Innovation (0-10)"
                  type="number"
                  fullWidth
                  InputProps={{ inputProps: { min: 0, max: 10 } }}
                  value={criteria.innovation}
                  onChange={(e) => handleCriteriaChange(e, "innovation")}
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  label="Viva (0-10)"
                  type="number"
                  fullWidth
                  InputProps={{ inputProps: { min: 0, max: 10 } }}
                  value={criteria.viva}
                  onChange={(e) => handleCriteriaChange(e, "viva")}
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  label="Report Submission (0-10)"
                  type="number"
                  fullWidth
                  InputProps={{ inputProps: { min: 0, max: 10 } }}
                  value={criteria.reportSubmission}
                  onChange={(e) => handleCriteriaChange(e, "reportSubmission")}
                />
              </Grid>
            </Grid>
          </Grid>
        </form>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="primary">
          Cancel
        </Button>
        <Button onClick={handleEvaluate} color="primary" disabled={isSubmitted}>
          {isSubmitted ? "Evaluation Submitted" : "Submit Evaluation"}
        </Button>
      </DialogActions>
      <Dialog open={isConfirmationOpen} onClose={handleCloseConfirmation}>
        <DialogTitle>Confirm Evaluation Submission</DialogTitle>
        <DialogContent>
          Are you sure you want to submit the evaluation? Once submitted, you
          cannot edit it.
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseConfirmation} color="primary">
            Cancel
          </Button>
          <Button onClick={handleEvaluate} color="primary">
            Confirm
          </Button>
        </DialogActions>
      </Dialog>
    </Dialog>
  );
};

export default EvaluationForm;
