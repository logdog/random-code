//
//  OptionsScreen.swift
//  Twister
//
//  Created by Logan Dihel on 6/11/16.
//  Copyright © 2016 Logan Dihel. All rights reserved.
//

import Foundation
import UIKit

class OptionsScreen : UIViewController {
    
    @IBOutlet weak var sbrLabel: UILabel!
    @IBOutlet weak var stepper: UIStepper!
    @IBOutlet weak var segment: UISegmentedControl!
    
    var back = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        var seconds = UserDefaults.standard.double(forKey: "Seconds")
        var index = UserDefaults.standard.integer(forKey: "Index")
        
        if seconds == 0 {
            seconds = 7
            index = 1
        }
        
        stepper.autorepeat = false
        stepper.minimumValue = 2
        stepper.maximumValue = 20
        stepper.stepValue = 1
        stepper.value = seconds
        
        sbrLabel.text = "Seconds Between Rounds: " + "\(Int(stepper.value))"
    
        segment.selectedSegmentIndex = index
        
        if(index == 0) {
            stepper.isUserInteractionEnabled = false
            sbrLabel.alpha = 0.5
        }
        
    }
    @IBAction func backTapped(_ sender: UIButton) {
        back = true
    }
    
    @IBAction func segmentTapped(_ sender: UISegmentedControl) {
        if segment.selectedSegmentIndex == 0 {
            sbrLabel.alpha = 0.5
            stepper.isUserInteractionEnabled = false
        }
        else {
            sbrLabel.alpha = 1.0
            stepper.isUserInteractionEnabled = true
        }
    }
    
    @IBAction func start(_ sender: UIButton) {
        back = false
    }
    @IBAction func stepperTapped(_ sender: AnyObject) {
        sbrLabel.text = "Seconds Between Rounds: " + "\(Int(stepper.value))"
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        
        UserDefaults.standard.set(stepper.value, forKey: "Seconds")
        UserDefaults.standard.set(segment.selectedSegmentIndex, forKey: "Index")
        
        if !back {
        let vc = segue.destination as! TwisterViewTwo
        if segment.selectedSegmentIndex > 0 {
            vc.timeBetweenTurns = stepper.value
        }
        if segment.selectedSegmentIndex == 2 {
            vc.automatic = true
        }
        }
    }
}
