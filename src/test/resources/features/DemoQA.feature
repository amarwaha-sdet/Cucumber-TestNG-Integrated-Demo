Feature: Test file upload and download functionality using DemoQA

  Background:
    Given user navigates to demoQA website

  @fileUploadAndDownload
  Scenario: File Upload and Download Test
    And user uploads a file and verifies file is uploaded
    And user downloads a file and verifies file is downloaded