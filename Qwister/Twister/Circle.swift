//
//  Circle.swift
//  Twister
//
//  Created by Logan Dihel on 6/18/16.
//  Copyright © 2016 Logan Dihel. All rights reserved.
//

import CoreGraphics
import UIKit

class Circle: UIView {
    
    var color = UIColor.black

    override func draw(_ rect: CGRect) {
        let path = UIBezierPath(ovalIn: rect)
        color.setFill()
        path.fill()
    }
    
    func setCircleColor(_ color: UIColor) {
        self.color = color
    }
    
}
