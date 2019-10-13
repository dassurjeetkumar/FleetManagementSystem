<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form >
<div class="page-content-wrapper">
		<div class="page-content">
		<div class="portlet-body form">
			<div class="row" style="overflow: hidden;">
				<div class="col-md-4" style=" overflow: hidden;">
				<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<!-- <i class="fa fa-gift"></i> -->
									Revenue							
							</div>
							<div class="tools">
								
							</div>
						</div>
						<div class="portlet-body" id="chart_live_display">
						<!-- <H4> Total Vehicle Data</H4> -->
						<div class="scroller" style="height:200px" data-always-visible="1" data-rail-visible="1" data-rail-color="blue" data-handle-color="blue">
							<div id="revenue_chart" class="chart"></div>
						</div>
						</div>
					</div>					
				</div>
				<div class="col-md-4" style=" overflow: hidden;">
				<div class="portlet box red">
						<div class="portlet-title">
							<div class="caption">
								<!-- <i class="fa fa-gift"></i> -->
								E-PARSE TRANSACTION							</div>
							<div class="tools">
								
							</div>
						</div>
						<div class="portlet-body" id="chart_live_display">
						<!-- <H4> Total Vehicle Data</H4> -->
						<div class="scroller" style="height:200px" data-always-visible="1" data-rail-visible="1" data-rail-color="blue" data-handle-color="red">
							<div id="transaction_chart" class="chart"></div>
						</div>
						</div>
					</div>					
				</div>
				<div class="col-md-4" style=" overflow: hidden;">
				<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<!-- <i class="fa fa-gift"></i> -->
								Lost/ Block Device	
							</div>
							<div class="tools">
								
							</div>
						</div>
						<div class="portlet-body" id="chart_live_display">
						<!-- <H4> Total Vehicle Data</H4> -->
						<div class="scroller" style="height:200px" data-always-visible="1" data-rail-visible="1" data-rail-color="blue" data-handle-color="green">
							<div id="lost_block_device" class="chart"></div>
						</div>
						</div>
					</div>					
				</div>
				
				</div>
				<div class="row" style="overflow: hidden;">
					<div class="col-md-4" style=" overflow: hidden;">
				<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<!-- <i class="fa fa-gift"></i> -->
								Lost/ Block Card	
							</div>
							<div class="tools">
								
							</div>
						</div>
						<div class="portlet-body" id="chart_live_display">
						<!-- <H4> Total Vehicle Data</H4> -->
						<div class="scroller" style="height:200px" data-always-visible="1" data-rail-visible="1" data-rail-color="blue" data-handle-color="blue">
							<div id="lost_block_card" class="chart"></div>
						</div>
						</div>
					</div>					
				</div>
				<div class="col-md-4" style=" overflow: hidden;">
				<div class="portlet box red">
						<div class="portlet-title">
							<div class="caption">
								<!-- <i class="fa fa-gift"></i> -->
								Ad Status	
							</div>
							<div class="tools">
								
							</div>
						</div>
						<div class="portlet-body" id="chart_live_display">
						<!-- <H4> Total Vehicle Data</H4> -->
						<div class="scroller" style="height:200px" data-always-visible="1" data-rail-visible="1" data-rail-color="blue" data-handle-color="red">
							<div id="ad_status" class="chart"></div>
						</div>
						</div>
					</div>	
					</div>
						<div class="col-md-4" style=" overflow: hidden;">
				<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<!-- <i class="fa fa-gift"></i> -->
								Manual Ticketing	
							</div>
							<div class="tools">
								
							</div>
						</div>
						<div class="portlet-body" id="chart_live_display">
						<!-- <H4> Total Vehicle Data</H4> -->
						<div class="scroller" style="height:200px" data-always-visible="1" data-rail-visible="1" data-rail-color="blue" data-handle-color="green">
							<div id="manual_ticket" class="chart"></div>
						</div>
						</div>
					</div>					
								
				</div>				
				</div>
			</div>
		</div>
	</div>
</form>