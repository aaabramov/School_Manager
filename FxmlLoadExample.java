private void createTest(Stage stage) throws SceneException{
        
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = null;
        TestFragmentController testController;
        try {
        
            loader.setLocation(getClass().getResource("view/TestFragment.fxml"));

            root = (AnchorPane)loader.load();
            root = null;
            testController = loader.getController();
            testController.setMainApp(this);

        } catch (IOException e){
            
            System.out.println("Error loading view/TestFragments.fxml file, message: " + e.getMessage());
            
        }
        if (root != null){
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            throw (new SceneException());
        }
        
        
        
      
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        try { 
            createTest(stage);
            System.out.println("no errors occuried");
        } catch (SceneException e){
            
            System.out.println(e.getMessage());
            
        } finally {
            
            
            
        }
      
      
      
        
      /*  initDatabase();
        initRootLayout();
        initLoginWindow();
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
        
    }
